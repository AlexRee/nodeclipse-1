package org.nodeclipse.java.avatarjs.launch;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.debug.core.model.RuntimeProcess;
import org.eclipse.jface.preference.IPreferenceStore;
import org.nodeclipse.common.preferences.CommonDialogs;
import org.nodeclipse.debug.util.Constants;
import org.nodeclipse.debug.util.VariablesUtil;
import org.nodeclipse.java.avatarjs.preferences.AvatarjsConstants;
import org.nodeclipse.ui.Activator;
//import org.nodeclipse.ui.preferences.PreferenceConstants;
import org.nodeclipse.ui.util.NodeclipseConsole;

/**
 * Launching `jjs` from Java 8.<br>
 * see LaunchConfigurationDelegate in .debug and .phantomjs module for comparison.
 * 
 * @since 0.7
 * @author Paul Verest
 */
public class LaunchConfigurationDelegate implements ILaunchConfigurationDelegate {

	@Override
	public void launch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {

		//NodeclipseConsole.write("launch jjs\n");
		
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		boolean isDebugMode = mode.equals(ILaunchManager.DEBUG_MODE);
		
		// Using configuration to build command line	
		List<String> cmdLine = new ArrayList<String>();

		String jjsPath= preferenceStore.getString(AvatarjsConstants.JAVA_PATH);
		// Check if the node location is correctly configured
		File jjsFile = new File(jjsPath);
		if(!jjsFile.exists()){
			// If the location is not valid than show a dialog which prompts the user to goto the preferences page
//			Dialogs.showPreferencesDialog("path to jjs util from Java 8 runtime is not correctly configured.\n\n"
//					+ "Please goto Window -> Prefrences -> Nodeclipse and configure the correct location under 'JJS path:'");
			CommonDialogs.showPreferencesDialog(AvatarjsConstants.PREFERENCES_PAGE,
					"Java 8 Nashorn jjs location is not correctly configured.\n\n"
					+ "Please goto Window -> Preferences -> "+AvatarjsConstants.PREFERENCE_PAGE_NAME
					+" and configure the correct location");
			return;
		}			
		cmdLine.add(jjsPath);
		
		if (isDebugMode) {
			//TODO research how to debug
		}

		String file = configuration.getAttribute("KEY_FILE_PATH",	"");
		String filePath = ResourcesPlugin.getWorkspace().getRoot().findMember(file).getLocation().toOSString();
		// path is relative, so cannot find it, unless get absolute path
		cmdLine.add(filePath);
		
		File workingPath = null;
		String workingDirectory = configuration.getAttribute(Constants.ATTR_WORKING_DIRECTORY, "");
		if(workingDirectory.length() > 0) {
			workingDirectory = VariablesUtil.resolveValue(workingDirectory);
			if(workingDirectory != null) {
				workingPath = new File(workingDirectory);
			}
		}
		if (workingPath == null){
			workingPath = (new File(filePath)).getParentFile();
		}
		
		Map<String, String> envm = new HashMap<String, String>();
		envm = configuration.getAttribute(Constants.ATTR_ENVIRONMENT_VARIABLES, envm);
		String[] envp = new String[envm.size()];
		int idx = 0;
		for(String key : envm.keySet()) {
			String value = envm.get(key);
			envp[idx++] = key + "=" + value;
		}
		
		
		for(String s : cmdLine) NodeclipseConsole.write(s+" ");
		NodeclipseConsole.write("\n");
		
		String[] cmds = {};
		cmds = cmdLine.toArray(cmds);
		// Launch a process to debug.eg,
		Process p = DebugPlugin.exec(cmds, workingPath, envp);
		RuntimeProcess process = (RuntimeProcess)DebugPlugin.newProcess(launch, p, AvatarjsConstants.PROCESS_MESSAGE);
		if (isDebugMode) {
			//TODO research how to debug
		}
		
	}

}
