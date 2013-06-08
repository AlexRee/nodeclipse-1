package org.nodeclipse.ui.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.nodeclipse.ui.Activator;

/**
 * @author Tomoyuki Inagaki
 * @author Paul Verest
 */
public class NodePreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    private FileFieldEditor fileFieldEditor;
    private FileFieldEditor expressPath;
    private FileFieldEditor coffeePath;
    //private FileFieldEditor completionsPath;
    
    public NodePreferencePage() {
        super(GRID);
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
        setDescription("Node Settings");
    }

    @Override
    public void init(IWorkbench workbench) {

    }

    @Override
    protected void createFieldEditors() {
        fileFieldEditor = new FileFieldEditor(PreferenceConstants.NODE_PATH, "Node Path:", getFieldEditorParent());
        addField(fileFieldEditor);
        
        expressPath = new FileFieldEditor(PreferenceConstants.EXPRESS_PATH, "Express Path:", getFieldEditorParent());
        addField(expressPath);

        coffeePath = new FileFieldEditor(PreferenceConstants.COFFEE_PATH, "Coffee Path:", getFieldEditorParent());
        addField(coffeePath);
        
//        completionsPath = new FileFieldEditor(PreferenceConstants.COMPLETIONS_JSON_PATH, "Completions.json Path:", getFieldEditorParent());
//        addField(completionsPath);
    }

    @Override
    public boolean isValid() {
        return super.isValid();
    }

}