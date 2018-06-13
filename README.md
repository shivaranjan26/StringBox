# StringBox

# Welcome to StringBox

## You can manage all your database operations with this library. Every information will be saved as Strings only in this library. This library can convert your images also to strings. You can use the encryption options also to encrypt your data. Follow the below methods to handle the library methods for your usage


### Create Database
StringBox.getInstance(this)





### Create Table

ArrayList< String > data = new ArrayList<>();</n>      
        data.add(COLUMN_NAME); </n>  
        data.add(COLUMN_GENDER);</n> 
        data.add(COLUMN_DOB);</n>  
        StringBox.getInstance(this).createTable(TABLE_NAME, data);
        
#### The above code will create a table with 3 columns, Name, Gender and DOB.





### Create Row

HashMap< String, String > map = new HashMap<>(); </br> 
        map.put(COLUMN_NAME, "Shiva"); </br>  
        map.put(COLUMN_GENDER, "Male"); </br>  
        map.put(COLUMN_DOB, "26/04/1989"); </br>  
        StringBox.getInstance(this).addRow(TABLE_NAME, map); </br>  
        map.put(COLUMN_NAME, "Mirna"); </br>  
        map.put(COLUMN_GENDER, "Female"); </br> 
        map.put(COLUMN_DOB, "18/06/1990"); </br> 
        StringBox.getInstance(this).addRow(TABLE_NAME, map);
        
  #### The above code will create two rows of data. One with name Shiva, Male and 26/04/1989 and the other row as Mirna, Female and 18/06/1990
  
  
  
  
  
### Get All rows from one Specific Column
  
  ArrayList< String > names = StringBox.getInstance(this).getAllRowsFromColumn(TABLE_NAME, COLUMN_NAME);
    
 #### The above code will get all the rows from column "Name".
 
 
 
 
 
### Modify a specific row
HashMap< String, String > map = new HashMap<>();</br>
        map.put(COLUMN_NAME, "Shiva");</br>
        map.put(COLUMN_GENDER, "Male");</br>
        map.put(COLUMN_DOB, "26/04/1989");</br>
        StringBox.getInstance(this).modifyRow(TABLE_NAME, map, rowToModify);
#### The above code will modify the row specified with the new values provided in hashmap. rowToModify is an int variable.





### Get the total rows count from a Table
StringBox.getInstance(this).getRowsCount(TABLE_NAME)
#### The above count will return the total rows count from the table name provided.





### Delete a Specific row from Table
StringBox.getInstance(this).deleteRow(TABLE_NAME, rowToDelete);
#### The above code will delete the row in the specified position. rowToDelete is a int variable





### Encrypt you String using AES
StringBoxUtils.encryptAES("myName", "shiva");
#### The above code will encrypt the string "Shiva" using AES algorithim and return the encrypted string. This can later be decrypted using the password given which is "myName". The password value is your choice.





### Decrypt you String
StringBoxUtils.decryptAES("myName", encodedStr)
#### The above code will decrypt the encodedStr passed using the password and return the decrypted string.

### Encryption CHARSETS
StringBoxUtils.CHARSET_UTF8.setEncryptionCharset(StringBoxUtils.CHARSET_UTF8); </br>
or </br>
StringBoxUtils..CHARSET_UTF8.setEncryptionCharset(StringBoxUtils.CHARSET_UTF16);
#### You can customize your encoding charset using the above lines. UTF-8 or UTF-16





### Convert Image to String
StringBoxUtils.bitMapToString(bitmap) </br>
or </br>
StringBoxUtils.bitMapToString(drawable)
#### Use the above code to convert your images to Strings and save in database





### Convert Strings To Images
StringBoxUtils.bitmapFromString(bitmapString)
#### Use the above code to retrieve the image from the string





### Shared Preferences - Set Preferences
StringBox.saveToPreferences(String key, String value) </br>
StringBox.saveToPreferences(String key, boolean value) </br>
StringBox.saveToPreferences(String key, int value) </br>
StringBox.saveToPreferences(String key, float value) </br>
StringBox.saveToPreferences(String key, long value) </br>
StringBox.saveToPreferences(String key, ArrayList<String> value) </br>
StringBox.saveToPreferences(String key, List<String> value) </br>
#### Use the above codes to save to shared preferences





### Shared Preferences - Get Preferences
getStringPreference(key) </br>
getBooleanPreference(key) </br>
getIntegerPreference(key) </br>
getFloatPreference(key) </br>
getLongPreference(key) </br>
getStringArrayPreference(key) - Returns as ArrayList< String > </br>
#### Use the above codes to retrieve the values from sharedpreferences.
