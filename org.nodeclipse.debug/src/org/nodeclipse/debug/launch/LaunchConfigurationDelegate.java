package org.nodeclipse.debug.launch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.chromium.debug.core.ChromiumDebugPlugin;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.swt.widgets.Display;
import org.nodeclipse.debug.util.Constants;
import org.nodeclipse.debug.util.NodeDebugUtil;
import org.nodeclipse.ui.Activator;
import org.nodeclipse.ui.preferences.PreferenceConstants;

public class LaunchConfigurationDelegate implements
		ILaunchConfigurationDelegate {
	private static Process nodeProcess = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.debug.core.model.ILaunchConfigurationDelegate#launch(org.
	 * eclipse.debug.core.ILaunchConfiguration, java.lang.String,
	 * org.eclipse.debug.core.ILaunch,
	 * org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void launch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {
		if(nodeProcess != null) {
			throw new CoreException(new Status(IStatus.OK, ChromiumDebugPlugin.PLUGIN_ID, null, null));
		}
		
		// Using configuration to build command line
		List<String> cmdLine = new ArrayList<String>();
		// Application path should be stored in preference.
		cmdLine.add(Activator.getDefault().getPreferenceStore()
				.getString(PreferenceConstants.NODE_PATH));
		if (mode.equals(ILaunchManager.DEBUG_MODE)) {
			cmdLine.add("--debug-brk=5858");
		}
		String file = configuration.getAttribute(Constants.KEY_FILE_PATH,
				Constants.BLANK_STRING);
		String filePath = ResourcesPlugin.getWorkspace().getRoot()
				.findMember(file).getLocation().toOSString();
		// path is relative, so can not found it.
		cmdLine.add(filePath);
		String[] cmds = {};
		cmds = cmdLine.toArray(cmds);
		// Launch a process to debug.eg,
		Process p = DebugPlugin
				.exec(cmds, (new File(filePath)).getParentFile());
		DebugPlugin.newProcess(launch, p, Constants.PROCESS_MESSAGE);
		// if (mode.equals(ILaunchManager.DEBUG_MODE)) {
		// DebugTarget target = new DebugTarget(launch, process, p);
		// launch.addDebugTarget(target);
		// }
		if (mode.equals(ILaunchManager.DEBUG_MODE)) {
			NodeDebugUtil.launch(mode, launch, monitor);
		}
		nodeProcess = p;
	}
	
	public static void terminateNodeProcess() {
		if(nodeProcess != null) {
			nodeProcess.destroy();
			nodeProcess = null;
		}
	}
}
