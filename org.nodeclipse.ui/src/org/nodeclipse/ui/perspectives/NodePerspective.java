package org.nodeclipse.ui.perspectives;

import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;

/**
 * This class is meant to serve as an example for how various contributions are
 * made to a perspective. Note that some of the extension point id's are
 * referred to as API constants while others are hardcoded and may be subject to
 * change.
 */
public class NodePerspective implements IPerspectiveFactory {

    public static final String ID = "org.nodeclipse.ui.perspectives.NodePerspective";
    private IPageLayout factory;

    public NodePerspective() {
        super();
    }

    public void createInitialLayout(IPageLayout factory) {
        this.factory = factory;
        addViews();
        addActionSets();
        addNewWizardShortcuts();
        addPerspectiveShortcuts();
        addViewShortcuts();
    }

    private void addViews() {
        // Creates the overall folder layout.
        // Note that each new Folder uses a percentage of the remaining
        // EditorArea.

        IFolderLayout left = factory.createFolder("left", IPageLayout.LEFT, 0.25f, factory.getEditorArea());
        left.addView(IPageLayout.ID_PROJECT_EXPLORER);

        IFolderLayout rightBottom = factory.createFolder("rightBottom", IPageLayout.BOTTOM, 0.75f, factory.getEditorArea());// NON-NLS-1
        rightBottom.addView(IConsoleConstants.ID_CONSOLE_VIEW);
        rightBottom.addView(IPageLayout.ID_PROBLEM_VIEW);

        IFolderLayout rightTopRight = factory.createFolder("rightTopRight", IPageLayout.RIGHT, 0.75f, factory.getEditorArea());
        rightTopRight.addView(IPageLayout.ID_OUTLINE);
        rightTopRight.addView(IPageLayout.ID_TASK_LIST);
    }

    private void addActionSets() {
        factory.addActionSet("org.eclipse.debug.ui.launchActionSet"); // NON-NLS-1
        factory.addActionSet("org.eclipse.debug.ui.debugActionSet"); // NON-NLS-1
        factory.addActionSet("org.eclipse.debug.ui.profileActionSet"); // NON-NLS-1
        factory.addActionSet("org.eclipse.team.ui.actionSet"); // NON-NLS-1
        factory.addActionSet(IPageLayout.ID_NAVIGATE_ACTION_SET); // NON-NLS-1
    }

    private void addPerspectiveShortcuts() {
        factory.addPerspectiveShortcut("org.eclipse.team.ui.TeamSynchronizingPerspective"); // NON-NLS-1
        factory.addPerspectiveShortcut("org.eclipse.ui.resourcePerspective"); // NON-NLS-1
        factory.addPerspectiveShortcut(IDebugUIConstants.ID_DEBUG_PERSPECTIVE);
    }

    private void addNewWizardShortcuts() {
        factory.addNewWizardShortcut("org.nodeclipse.ui.wizards.NodeProjectWizard"); // NON-NLS-1
        factory.addNewWizardShortcut("org.nodeclipse.ui.wizards.ExpressProjectWizard"); // NON-NLS-1
        factory.addNewWizardShortcut("org.nodeclipse.phantomjs.wizards.PhantomjsProjectWizard"); // NON-NLS-1 @since 0.8
        factory.addNewWizardShortcut("org.nodeclipse.jjs.wizards.NashornProjectWizard"); // NON-NLS-1 @since 0.8
        factory.addNewWizardShortcut("org.nodeclipse.vertx.wizards.VertxProjectWizard"); // NON-NLS-1 @since 0.11
        factory.addNewWizardShortcut("org.eclipse.ui.wizards.new.project"); // NON-NLS-1
        // auto new section
        factory.addNewWizardShortcut("org.nodeclipse.ui.wizards.NodeFileWizard"); // NON-NLS-1
        factory.addNewWizardShortcut("org.nodeclipse.ui.wizards.CoffeeFileWizard"); // NON-NLS-1
        factory.addNewWizardShortcut("org.nodeclipse.ui.wizards.TypeScriptFileWizard"); // NON-NLS-1
        factory.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder"); // NON-NLS-1
        factory.addNewWizardShortcut("org.eclipse.ui.wizards.new.file");// NON-NLS-1
        //TODO Tern Wizards
        // Tern JSON type def
        // Tern (Server) Plugin
        // Tern with Ace
        // Tern with Orion
        // Tern with CodeMirror
        // http://ternjs.net/doc/manual.html#plugins
        // https://github.com/angelozerr/tern.java/wiki/Tern-Toolings
    }

    private void addViewShortcuts() {
		factory.addShowViewShortcut(IPageLayout.ID_TASK_LIST);
		factory.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
		factory.addShowViewShortcut(IPageLayout.ID_PROJECT_EXPLORER);
		factory.addShowViewShortcut(IPageLayout.ID_NAVIGATE_ACTION_SET);
    	 
        factory.addShowViewShortcut("org.eclipse.team.ui.GenericHistoryView"); // NON-NLS-1
        factory.addShowViewShortcut(IConsoleConstants.ID_CONSOLE_VIEW);
        factory.addShowViewShortcut("org.eclipse.ui.views.ResourceNavigator"); // NON-NLS-1
        factory.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
        factory.addShowViewShortcut(IPageLayout.ID_OUTLINE);
        factory.addShowViewShortcut(IPageLayout.ID_BOOKMARKS); //@since 0.8
        factory.addShowViewShortcut("com.eclipserunner.views.RunnerView"); // NON-NLS-1 @since 0.8
    }

}
