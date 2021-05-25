package Report;


import java.awt.Color;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.TextFormFieldType;

import ConstantsFramework.Constants_Framework;

public class Doc {	 

    public Doc(ArrayList<?> array, ArrayList<?> arrayText, String classe) throws Exception {
        int verticalPosition = 0;
        String dataPath = Constants_Framework.DOC_PATH;
        DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
        Date date = new Date();
        Document doc = new Document(dataPath);
        DocumentBuilder builder = new DocumentBuilder(doc);

        for (int i = 0; i < array.size(); i++) {
            String imagePath = (String) array.get(i);
            String text = (String) arrayText.get(i);
            FileInputStream in = new FileInputStream(imagePath);
            
            builder.getFont().setColor(Color.black);
            builder.getFont().setEmboss(false);
            builder.getFont().setEngrave(false);
            builder.getFont().setHighlightColor(Color.WHITE);
            
            builder.insertTextInput("", TextFormFieldType.REGULAR, "", "\n\n" + text, 0);
            
            builder.getFont().setColor(Color.black);
            builder.getFont().setEmboss(false);
            builder.getFont().setEngrave(false);
            builder.getFont().setHighlightColor(Color.WHITE);
            
            builder.insertImage(in, 540, 320);
            verticalPosition = verticalPosition + 400;
        }
        doc.save(Constants_Framework.EVIDENCIA_PATH + "Automation_Evid_"+ classe + "_" + dateFormat.format(date) + ".docx");
    }
}

