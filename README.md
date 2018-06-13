# StringBox

# Welcome to StringBox

## You can manage all your database operations with this library. Every information will be saved as Strings only in this library. This library can convert your images also to strings. You can use the encryption options also to encrypt your data. Follow the below methods to handle the library methods for your usage


### Create Database
StringBox.getInstance(this)


### Create Table
ArrayList<String> data = new ArrayList<>();
        data.add(COLUMN_NAME);
        data.add(COLUMN_GENDER);
        data.add(COLUMN_DOB);
        StringBox.getInstance(this).createTable(TABLE_NAME, data);
#### The above code will create a table with 3 columns, Name, Gender and DOB.
