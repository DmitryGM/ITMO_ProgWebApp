package com.company.bundle;
        
import java.util.ListResourceBundle;

public class Bundle_en extends ListResourceBundle {
    
    @Override
    protected Object[][] getContents()
    {
        return contents;
    }
    
    private Object[][] contents = {
            { "Button"   ,  "Select Point" },
            { "Title"   ,  "lab5; var-???" },
    };
}
