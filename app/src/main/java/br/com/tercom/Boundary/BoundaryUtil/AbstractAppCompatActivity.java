package br.com.tercom.Boundary.BoundaryUtil;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import br.com.tercom.Application.AppTercom;
import br.com.tercom.Enum.EnumFont;
import br.com.tercom.R;

import static br.com.tercom.Util.CustomTypeFace.overrideFonts;

/**
 * Created by Felipe on 14/06/2017.
 */

public abstract class AbstractAppCompatActivity extends AppCompatActivity {

    public void CreateToolbar(boolean haveHome) {
        Toolbar mToolbar = findViewById(R.id.include_toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        if(haveHome)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


//
//    public void createBottomMenu(final BottomNavigationView bottomNavigationView, int indice){
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    switch (item.getItemId()) {
//                        case R.id.destaques:
//                            createIntentAbs(HomeActivity.class);
//                            break;
//                        case R.id.item_login:
////                            if (USER_STATIC == null) {
//                                createIntentAbs(ProfileActivity.class);
////                            } else {
////                                createIntentAbs(PerfilControl.class);
////                            }
//                            break;
//                        case R.id.teatro:
//                            createIntentAbs(TheaterActivity.class);
//
//                            break;
//                        case R.id.busca:
//                            createIntentAbs(LoginActivity.class);
//
//                            break;
//                        case R.id.ajuda:
//                            createIntentAbs(PurchaseSSBoundary.class);
//
//                            break;
//                    }
//                return true;
//            }
//        });
////        bottomNavigationView.inflateMenu(R.menu.menu_navigation);
//        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
//        bottomNavigationView.getMenu().getItem(indice).setChecked(true);
//
//
//    }


    public void createIntentAbs(Class classe){
        Intent intent = new Intent();
        intent.setClass(this,classe);
        startActivity(intent);
    }


}
