package br.com.tercom.Boundary.BoundaryUtil;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import br.com.tercom.Application.AppTercom;
import br.com.tercom.Enum.EnumFont;
import br.com.tercom.R;
import br.com.tercom.Util.CustomTypeFace;

import static br.com.tercom.Util.CustomTypeFace.overrideFonts;
import static br.com.tercom.Util.CustomTypeFace.setFontSingleTxt;

/**
 * Created by Felipe on 14/06/2017.
 */

public abstract class AbstractAppCompatActivity extends AppCompatActivity {

    public void createToolbar() {
        Toolbar mToolbar = findViewById(R.id.include_toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        TextView textTitle = mToolbar.findViewById(R.id.textTitle);
         textTitle.setTypeface(setFontSingleTxt(AppTercom.getContext(),EnumFont.FONT_RNS));
    }


    public void createIntentAbs(Class classe){
        Intent intent = new Intent();
        intent.setClass(this,classe);
        startActivity(intent);
    }



    public void CreateToolbarWithNavigation(int index) {
        Toolbar mToolbar = findViewById(R.id.include_toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitleMarginBottom(10);
        setSupportActionBar(mToolbar);
        TextView textTitle = mToolbar.findViewById(R.id.textTitle);
        textTitle.setTypeface(setFontSingleTxt(AppTercom.getContext(), EnumFont.FONT_RNS));
        CreateNavigationDrawer(mToolbar, index);
    }


    public void CreateNavigationDrawer( Toolbar toolbar, int identifier)
    {
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withProfileImagesVisible(true)
                .withCompactStyle(true)
                .withDividerBelowHeader(true)
                .addProfiles(new ProfileDrawerItem().withEmail("felip.amalf@tercom.com.br").withName("Felipe Amalfi Lima"))
                .withTextColor(Color.BLACK)
                .build();

        Drawer drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .withSliderBackgroundColor(Color.WHITE)
                .withSelectedItem(identifier)
                .addDrawerItems(
                        CreateItem(1, "Menu", Color.BLACK, Color.WHITE, getResources().getColor(R.color.colorAccent), R.drawable.ic_tercom_logo),
                        CreateItem(2, "Produtos", Color.BLACK, Color.WHITE, getResources().getColor(R.color.colorAccent), R.drawable.ic_tercom_logo),
                        CreateItem(3, "Serviços", Color.BLACK, Color.WHITE, getResources().getColor(R.color.colorAccent), R.drawable.ic_tercom_logo),
                        CreateItem(4, "Cotações", Color.BLACK, Color.WHITE, getResources().getColor(R.color.colorAccent), R.drawable.ic_tercom_logo),
                        CreateItem(9, "Ajuda", Color.BLACK, Color.WHITE, getResources().getColor(R.color.colorAccent), R.drawable.ic_tercom_logo)

                ).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        return true;
                    }
                }).build();
    }

    public SecondaryDrawerItem CreateItem(int identifier, String name, int textColor, int selectedTextColor, int selectedColor, int icon)
    {
        return new SecondaryDrawerItem()
                .withIdentifier(identifier)
                .withName(name)
                .withTextColor(textColor)
                .withSelectedTextColor(selectedTextColor)
                .withSelectedColor(selectedColor)
                .withTypeface(CustomTypeFace.setFontSingleTxt(this, EnumFont.FONT_ROBOTO_REGULAR))
                .withIcon(icon);

    }


}
