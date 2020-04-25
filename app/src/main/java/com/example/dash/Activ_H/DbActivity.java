package com.example.dash.Activ_H;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dash.MainActivity;
import com.example.dash.R;
import com.example.dash.model.Users;
import com.example.dash.network.APIClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DbActivity extends MainActivity {


    GridView gridView;

    CustomAdapter customAdapter;

    public static List<Users> usersList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);


        gridView = findViewById(R.id.grindView);

        usersList = new ArrayList<>();

        //make network call

        Call<List<Users>> call = APIClient.apIinterface().getUserList();

        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if(response.isSuccessful()){

                    usersList = response.body();
                    customAdapter = new CustomAdapter(response.body(),DbActivity.this);

                    gridView.setAdapter(customAdapter);
                    //go to check details of registered when clicked
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            //intent
                            Intent intent = new Intent();
                        /*intent.putExtra("usernamme", usersList.get(i).getUsername());
                        intent.putExtra("name", usersList.get(i).getName());
                        intent.putExtra("surname", usersList.get(i).getSurname());
                        intent.putExtra("email", usersList.get(i).getEmail());
                        intent.putExtra("avatar", usersList.get(i).getAvatar());
*/

                            startActivity(new Intent(getApplicationContext(), UserDetails.class).putExtra("usernamme", usersList.get(i).getUsername()).putExtra("name", usersList.get(i).getName()).putExtra("surname", usersList.get(i).getSurname()).putExtra("email", usersList.get(i).getEmail()).putExtra("avatar", usersList.get(i).getAvatar()).putExtra("created", usersList.get(i).getCreatedAt()) );
                        }
                    });

                } else {
                    Toast.makeText(getApplicationContext(), "error ", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();


            }
        });
    }

    public class CustomAdapter extends BaseAdapter {

        public List<Users> userLists;
        public Context context;

        public CustomAdapter(List<Users> userLists, Context context) {
            this.userLists = userLists;
            this.context = context;
        }

        @Override
        public int getCount() {
            return userLists.size();
        }
        @Override
        public Object getItem(int i) {
            return null;
        }
        @Override
        public long getItemId(int i) {
            return i;
        }
        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            //set view for layout type from XML file row_data
            View view = LayoutInflater.from(context).inflate(R.layout.row_data, null);

            //find views

            TextView name = view.findViewById(R.id.textRow);
            ImageView avatar = view.findViewById(R.id.imageView);

            //set data
            name.setText(userLists.get(i).getUsername());
            //display picture from database to particular user
            Glide.with(context)
                    .load(userLists.get(i).getAvatar())
                    .into(avatar);

            return view;

        }
    }
    @Override
    public void onBackPressed() {
        final AlertDialog.Builder constructor = new AlertDialog.Builder(DbActivity.this);
        constructor.setMessage("Exit User`s databases list?");
        constructor.setCancelable(true);
        constructor.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });
        constructor.setPositiveButton("Yes, close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog message = constructor.create();
        message.show();
    }
}


