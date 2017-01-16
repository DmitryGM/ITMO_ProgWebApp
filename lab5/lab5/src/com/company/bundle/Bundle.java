package com.company.bundle;

import java.util.ListResourceBundle;

public class Bundle extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {
            { "Button"   ,  "Select Point"},
            { "Title"   ,  "lab4; var-20108"},
    };
}
