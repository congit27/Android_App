package com.android.baitapcontentprovide;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.baitapcontentprovide.adapter.ContactAdapter;
import com.android.baitapcontentprovide.model.Contact;

import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ASK_PERMISSIONS  = 1001;

    ListView lvContact;
    ArrayList<Contact> listContact;
    ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        addControls();
        showAllContractFromDevice();
    }

    private void showAllContractFromDevice() {
        // Thông qua ContactConstract để lấy contact trong điện thoại
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        //Trả về một Cursor - quản lí dữ liệu contact trong điện thoại
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        listContact.clear();
        while(cursor.moveToNext()) {
            // Lấy thông tin tên trong danh bạ
            String nameContact = ContactsContract.Contacts.DISPLAY_NAME;
            // Lấy thông tin số điện thoại trong danh bạ
            String phoneContact = ContactsContract.CommonDataKinds.Phone.NUMBER;
            // Lấy vị trí cột trong dữ liệu
            int positionName = cursor.getColumnIndex(nameContact);
            int positionPhone = cursor.getColumnIndex(phoneContact);
            // Lấy dữ liệu trong các cột ra
            String name = cursor.getString(positionName);
            String phone = cursor.getString(positionPhone);

            // Đưa vào mảng
            Contact contact = new Contact(name, phone, R.drawable.ic_launcher_foreground);
            listContact.add(contact);
        }
        adapter = new ContactAdapter(this, R.layout.contact_item, listContact);
        lvContact.setAdapter(adapter);


        // Chuyển dữ liệu
        transferData();
    }


    // Chuyển info khi click item list Contact
    private void transferData() {
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact = listContact.get(position);
                Intent intent = new Intent(ContactActivity.this, DetailActivity.class);

                intent.putExtra("name", contact.getName());
                intent.putExtra("phone", contact.getPhone());

                startActivity(intent);
            }
        });
    }


    private void addControls() {
        lvContact = findViewById(R.id.lvContact);
        listContact = new ArrayList<>();
    }
}
