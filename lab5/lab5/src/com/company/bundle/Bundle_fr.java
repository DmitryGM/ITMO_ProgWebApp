package com.company.bundle;

import java.util.ListResourceBundle;

public class Bundle_fr extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {
            { "Button"   ,  "Sélectionner un point"},
            { "Title"   ,  "lab5; var-???" },
    };
}
