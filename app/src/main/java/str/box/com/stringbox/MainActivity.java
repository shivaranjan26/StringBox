package str.box.com.stringbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String TABLE_NAME = "TestTable";

    String COLUMN_NAME = "Name";
    String COLUMN_GENDER = "Gender";
    String COLUMN_DOB = "DOB";

    int rowToModify = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*ArrayList<String> data = new ArrayList<>();
        data.add(COLUMN_NAME);
        data.add(COLUMN_GENDER);
        data.add(COLUMN_DOB);
        StringBox.getInstance(this).createTable(TABLE_NAME, data);

        addDataToTable();

        getDataFromColumn();

        modifyData();

        Toast.makeText(this, "" + StringBox.getInstance(this).getRowsCount(TABLE_NAME), Toast.LENGTH_SHORT).show();

        deleteRow();

        Toast.makeText(this, "" + StringBox.getInstance(this).getRowsCount(TABLE_NAME), Toast.LENGTH_SHORT).show();*/

        try {
            String encodedStr = StringBoxUtils.encryptAES("myName", "shiva");
            Toast.makeText(this, encodedStr , Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "" + StringBoxUtils.decryptAES("myName", encodedStr), Toast.LENGTH_SHORT).show();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        /*List<String> newstr = new ArrayList<>();
        newstr.add("1");
        newstr.add("2");
        StringBox.getInstance(this).saveToPreferences("key", newstr);

        StringBox.getInstance(this).getStringArrayPreference("key");*/
    }

    private void deleteRow() {
        StringBox.getInstance(this).deleteRow(TABLE_NAME, rowToModify);
        getDataFromColumn();
    }

    private void modifyData() {
        HashMap<String, String> map = new HashMap<>();
        map.put(COLUMN_NAME, "Shiva");
        map.put(COLUMN_GENDER, "Male");
        map.put(COLUMN_DOB, "26/04/1989");
        StringBox.getInstance(this).modifyRow(TABLE_NAME, map, rowToModify);

        getDataFromColumn();
    }

    private void getDataFromColumn() {
        ArrayList<String> names = StringBox.getInstance(this).getAllRowsFromColumn(TABLE_NAME, COLUMN_NAME);
        String str = "";
        for (int i=0; i<names.size(); i++){
            str = str + names.get(i) + " ";
            if (names.get(i).equalsIgnoreCase("Mirna")) {
                rowToModify = i;
            }
        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    private void addDataToTable() {
        HashMap<String, String> map = new HashMap<>();
        map.put(COLUMN_NAME, "Shiva");
        map.put(COLUMN_GENDER, "Male");
        map.put(COLUMN_DOB, "26/04/1989");
        StringBox.getInstance(this).addRow(TABLE_NAME, map);

        map.put(COLUMN_NAME, "Mirna");
        map.put(COLUMN_GENDER, "Female");
        map.put(COLUMN_DOB, "18/06/1990");
        StringBox.getInstance(this).addRow(TABLE_NAME, map);
    }
}
