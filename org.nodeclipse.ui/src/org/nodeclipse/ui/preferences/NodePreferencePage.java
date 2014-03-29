package org.nodeclipse.ui.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.nodeclipse.ui.Activator;
import org.nodeclipse.ui.util.VersionUtil;

/**
 * @author Tomoyuki Inagaki
 * @author Paul Verest
 */
public class NodePreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    private BooleanFieldEditor nodeclipseConsoleEnabled;

	private BooleanFieldEditor nodeJustNode;
    private FileFieldEditor nodePath;
    private StringFieldEditor nodeOptions;
    private StringFieldEditor nodeApplicationArguments;
    private BooleanFieldEditor nodeAllowMany;
    private BooleanFieldEditor nodePassAllEnvVars;
    private DirectoryFieldEditor nodeSourcesPath;
    private BooleanFieldEditor useNodejsBaseModuleDefinitions;
    private BooleanFieldEditor useOrionIndexFiles;
    private BooleanFieldEditor useCompletionJson;
    private FileFieldEditor completionsPath;
    private BooleanFieldEditor nodeDebugNoBreak;
    private IntegerFieldEditor nodeDebugPort;
    private FileFieldEditor nodeMonitorPath;
    private FileFieldEditor expressPath;
    private FileFieldEditor coffeePath;
    //private BooleanFieldEditor coffeeJustCoffee;
    private StringFieldEditor coffeeCompileOptions;
    private StringFieldEditor coffeeCompileOutputFolder;
    private FileFieldEditor typescriptCompilerPath;
    private StringFieldEditor typescriptCompilerOptions;

    public NodePreferencePage() {
        super(GRID);
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
        setDescription(
        	VersionUtil.getLongString()+
        	"\n"+
    		"Node.js, Express, CoffeeScript, TypeScript settings");
    }

	@Override
    public void init(IWorkbench workbench) {
    }

    @Override
    protected void createFieldEditors() {

    	//TODO possible to copy version string
		//    	String verString = VersionUtil.getLongString();
		//        nodeclipseVersionsString = new StringFieldEditor(PreferenceConstants.NODE_OPTIONS, "Node options (node -h):", getFieldEditorParent());
		//        nodeclipseVersionsString.setStringValue(verString);
		//        nodeclipseVersionsString.setTextLimit(verString.length());
		//        nodeclipseVersionsString.setEnabled(false, (Composite) this);
		//        addField(nodeclipseVersionsString);

    	
        nodeclipseConsoleEnabled = new BooleanFieldEditor(PreferenceConstants.NODECLIPSE_CONSOLE_ENABLED, 
        		"enable Nodeclipse Console", getFieldEditorParent());
        addField(nodeclipseConsoleEnabled);

        nodeJustNode = new BooleanFieldEditor(PreferenceConstants.NODE_JUST_NODE, 
        		"find node on PATH. Otherwise use Node.js instance in location below", getFieldEditorParent());
        addField(nodeJustNode);

        nodePath = new FileFieldEditor(PreferenceConstants.NODE_PATH, "Node.js path:", getFieldEditorParent());
        addField(nodePath);
        
        nodeOptions = new StringFieldEditor(PreferenceConstants.NODE_OPTIONS, "Node options (node -h):", getFieldEditorParent());
        addField(nodeOptions);

        nodeApplicationArguments = new StringFieldEditor(PreferenceConstants.NODE_APPLICATION_ARGUMENTS, "Node Application arguments:", getFieldEditorParent());
        addField(nodeApplicationArguments);
        
        nodeAllowMany = new BooleanFieldEditor(PreferenceConstants.NODE_ALLOW_MANY, 
        		"allow many Node.js instances running", getFieldEditorParent());
        addField(nodeAllowMany);
        
        nodePassAllEnvVars = new BooleanFieldEditor(PreferenceConstants.NODE_PASS_ALL_ENVIRONMENT_VARIABLES, 
        		"pass all environment variables of Eclipse to launched Node.js app", getFieldEditorParent());
        addField(nodePassAllEnvVars);

        nodeSourcesPath = new DirectoryFieldEditor(PreferenceConstants.NODE_SOURCES_PATH, "Node sources directory path:", getFieldEditorParent());
        addField(nodeSourcesPath);

        useNodejsBaseModuleDefinitions = new BooleanFieldEditor(PreferenceConstants.USE_NODEJS_BASE_MODULE_DEFINITIONS, 
        		"use Node.js base module definitions (changed after restart)", getFieldEditorParent());
        addField(useNodejsBaseModuleDefinitions);
        useOrionIndexFiles = new BooleanFieldEditor(PreferenceConstants.USE_ORION_INDEX_FILES, 
        		"use Orion IndexFiles (changed after restart)", getFieldEditorParent());
        addField(useOrionIndexFiles);
        useCompletionJson = new BooleanFieldEditor(PreferenceConstants.USE_COMPLETIONS_JSON, 
        		"use completion.json (changed after restart)", getFieldEditorParent());
        addField(useCompletionJson);

        completionsPath = new FileFieldEditor(PreferenceConstants.COMPLETIONS_JSON_PATH, "Alternative completions.json path:", getFieldEditorParent());
        addField(completionsPath);
    	
        // "Node debug no -break (disable interruption of Node.js app on first line, check debug Help)" would make dialog wider
        nodeDebugNoBreak = new BooleanFieldEditor(PreferenceConstants.NODE_DEBUG_NO_BREAK, 
        		"Node debug without -brk (disable interruption of Node.js app)", getFieldEditorParent());
        addField(nodeDebugNoBreak);

        nodeDebugPort = new IntegerFieldEditor(PreferenceConstants.NODE_DEBUG_PORT, "Node debug port:", getFieldEditorParent());
        addField(nodeDebugPort);

        nodeMonitorPath = new FileFieldEditor(PreferenceConstants.NODE_MONITOR_PATH, "Node monitor path:", getFieldEditorParent());
        addField(nodeMonitorPath);

        expressPath = new FileFieldEditor(PreferenceConstants.EXPRESS_PATH, "Express path:", getFieldEditorParent());
        addField(expressPath);

        coffeePath = new FileFieldEditor(PreferenceConstants.COFFEE_PATH, "Coffee path:", getFieldEditorParent());
        addField(coffeePath);

		//        coffeeJustCoffee = new BooleanFieldEditor(PreferenceConstants.COFFEE_JUST_COFFEE, 
		//        		"just coffee (let Node.js find coffee CLI)", getFieldEditorParent());
		//        addField(coffeeJustCoffee);

        coffeeCompileOptions = new StringFieldEditor(PreferenceConstants.COFFEE_COMPILE_OPTIONS, "Coffee compile options:", getFieldEditorParent());
        addField(coffeeCompileOptions);

        coffeeCompileOutputFolder = new StringFieldEditor(PreferenceConstants.COFFEE_COMPILE_OUTPUT_FOLDER, "Coffee output folder #76", getFieldEditorParent());
        addField(coffeeCompileOutputFolder);

        typescriptCompilerPath = new FileFieldEditor(PreferenceConstants.TYPESCRIPT_COMPILER_PATH, "TypeScript compiler path:", getFieldEditorParent());
        addField(typescriptCompilerPath);

        typescriptCompilerOptions = new StringFieldEditor(PreferenceConstants.TYPESCRIPT_COMPILER_OPTIONS, "TypeScript compiler options:", getFieldEditorParent());
        addField(typescriptCompilerOptions);

    }

    @Override
    public boolean isValid() {
        return super.isValid();
    }

}