package com.quebecambiental.controledefrota;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.quebecambiental.controledefrota.listas.AdapterListaMotorista;
import com.quebecambiental.controledefrota.negocio.Motorista;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Motorista> motoristas;
    private AdapterListaMotorista mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        motoristas = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(this);

        RecyclerView rvListaMotoristas = findViewById(R.id.rvListaMotoristas);
        rvListaMotoristas.setLayoutManager(mLayoutManager);

        mAdapter = new AdapterListaMotorista(motoristas);
        rvListaMotoristas.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference refMotoristas = database.getReference("motoristas");

        /*refMotoristas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                List<Motorista> motoristas = new ArrayList<>();

                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    Motorista motorista = new Motorista();
                    motorista.setNome(childSnapshot.child("nome").getValue().toString());
                    motorista.setNumeroCelular(childSnapshot.child("numeroCelular").getValue().toString());
                    motorista.setCodigo(Integer.parseInt(childSnapshot.child("cod").getValue().toString()));
                    motorista.setRfId(childSnapshot.child("rfId").getValue().toString());

                    motoristas.add(motorista);
                }

                Log.d("CONTROLE", "Value is: " + dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("CONTROLE", "Failed to read value.", error.toException());
            }
        });*/

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d("ControleFrota", "onChildAdded:" + dataSnapshot.getKey());

                Motorista motorista = dataSnapshot.getValue(Motorista.class);
                motoristas.add(motorista);

                mAdapter.notifyItemInserted(Integer.parseInt(dataSnapshot.getKey()));

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d("ControleFrota", "onChildChanged:" + dataSnapshot.getKey());

                Motorista motoristaAlterado = dataSnapshot.getValue(Motorista.class);
                motoristas.get(Integer.parseInt(dataSnapshot.getKey())).setNome(motoristaAlterado.getNome());
                motoristas.get(Integer.parseInt(dataSnapshot.getKey())).setNumeroCelular(motoristaAlterado.getNumeroCelular());
                motoristas.get(Integer.parseInt(dataSnapshot.getKey())).setRfId(motoristaAlterado.getRfId());
                motoristas.get(Integer.parseInt(dataSnapshot.getKey())).setCodigo(motoristaAlterado.getCodigo());

                mAdapter.notifyItemChanged(Integer.parseInt(dataSnapshot.getKey()));

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d("ControleFrota", "onChildRemoved:" + dataSnapshot.getKey());

                // A comment has changed, use the key to determine if we are displaying this
                // comment and if so remove it.
                Motorista motorista = dataSnapshot.getValue(Motorista.class);
                motoristas.remove(motorista);

                mAdapter.notifyItemRemoved(Integer.parseInt(dataSnapshot.getKey()));
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d("ControleFrota", "onChildMoved:" + dataSnapshot.getKey());

                // A comment has changed position, use the key to determine if we are
                // displaying this comment and if so move it.
                Motorista movedComment = dataSnapshot.getValue(Motorista.class);
                String commentKey = dataSnapshot.getKey();

                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("ControleFrota", "postComments:onCancelled", databaseError.toException());
                Toast.makeText(getApplicationContext(), "Failed to load comments.",
                        Toast.LENGTH_SHORT).show();
            }
        };
        refMotoristas.addChildEventListener(childEventListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
