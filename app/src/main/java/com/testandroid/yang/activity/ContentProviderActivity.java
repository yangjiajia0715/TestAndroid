package com.testandroid.yang.activity;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import com.testandroid.yang.R;

import java.util.ArrayList;

/**
 * ContentProviderActivity
 * Created by yangjiajia on 2017/9/15.
 */

public class ContentProviderActivity extends BaseActivity {
    private static final String TAG = "ContentProviderActivity";

    private int posTemp;

    public static void start(Context context) {
        Intent starter = new Intent(context, ContentProviderActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        initView();
        initData();
    }

    @Override
    public void initView() {
//        CursorLoader loader = new CursorLoader()
        String authority = "com.yang.hhhh";

//        new ContentProviderOperation.Builder().withValueBackReference("", "");
        ArrayList<ContentProviderOperation> ops = new ArrayList<>();

    /*
     * Creates a new raw contact with its account type (server type) and account name
     * (user's account). Remember that the display name is not stored in this row, but in a
     * StructuredName data row. No other data is required.
     */
        ContentProviderOperation.Builder op =
                ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                        .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, "yang_type")
                        .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, "yang_name");

        // Builds the operation and adds it to the array of operations
        ops.add(op.build());
//        try {
//            getContentResolver().applyBatch(authority, ops);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        } catch (OperationApplicationException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void initData() {
        findViewById(R.id.content_temp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Uri[] uris = {ContactsContract.Contacts.CONTENT_URI,
//                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                        ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI,
//                        ContactsContract.CommonDataKinds.Email.CONTENT_URI};
//
//                Log.d(TAG, "onClick: posTemp=" + posTemp + ",posTemp=" + uris[posTemp % uris.length]);
//
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setData(uris[posTemp % uris.length]);
//                startActivity(intent);
//                posTemp++;
                insertContact();
            }
        });
    }

    private void insertContact() {
        // Gets values from the UI
        String name = "_0920_name1";
        String phone = "_0920_phone1";
        String email = "_0920_email1";

        String company = "_0920_company1";
        String jobtitle = "_0920_jobtitle1";

// Creates a new intent for sending to the device's contacts application
        Intent insertIntent = new Intent(ContactsContract.Intents.Insert.ACTION);

// Sets the MIME type to the one expected by the insertion activity
        insertIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

// Sets the new contact name
        insertIntent.putExtra(ContactsContract.Intents.Insert.NAME, name);

// Sets the new company and job title
        insertIntent.putExtra(ContactsContract.Intents.Insert.COMPANY, company);
        insertIntent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobtitle);

/*
 * Demonstrates adding data rows as an array list associated with the DATA key
 */

// Defines an array list to contain the ContentValues objects for each row
        ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();


/*
 * Defines the raw contact row
 */

// Sets up the row as a ContentValues object
        ContentValues rawContactRow = new ContentValues();

// Adds the account type and name to the row
        rawContactRow.put(ContactsContract.RawContacts.ACCOUNT_TYPE, "_0920_account_type");
        rawContactRow.put(ContactsContract.RawContacts.ACCOUNT_NAME, "_0920_account_name");

// Adds the row to the array
        contactData.add(rawContactRow);

/*
 * Sets up the phone number data row
 */

// Sets up the row as a ContentValues object
        ContentValues phoneRow = new ContentValues();

// Specifies the MIME type for this data row (all data rows must be marked by their type)
        phoneRow.put(ContactsContract.Data.MIMETYPE,
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
        );

// Adds the phone number and its type to the row
        phoneRow.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phone);

// Adds the row to the array
        contactData.add(phoneRow);

/*
 * Sets up the email data row
 */

// Sets up the row as a ContentValues object
        ContentValues emailRow = new ContentValues();

// Specifies the MIME type for this data row (all data rows must be marked by their type)
        emailRow.put(
                ContactsContract.Data.MIMETYPE,
                ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE
        );

// Adds the email address and its type to the row
        emailRow.put(ContactsContract.CommonDataKinds.Email.ADDRESS, email);

// Adds the row to the array
        contactData.add(emailRow);

/*
 * Adds the array to the intent's extras. It must be a parcelable object in order to
 * travel between processes. The device's contacts app expects its key to be
 * Intents.Insert.DATA
 */
        insertIntent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);

// Send out the intent to start the device's contacts app in its add contact activity.
        startActivity(insertIntent);
    }
}
