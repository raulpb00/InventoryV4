package es.raulprieto.inventory.ui.base;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.navigation.NavigationView;

import es.raulprieto.inventory.R;
import es.raulprieto.inventory.databinding.ActivityBaseBinding;


public class BaseActivity extends AppCompatActivity {

    ActivityBaseBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_base);

        binding.content.fab.hide();

        setSupportActionBar((Toolbar) binding.content.toolbar);
        ((Toolbar) binding.content.toolbar).setTitle(R.string.btDependencies);
        // In order to display the Navigation button it must be shown as the Home indicator
        // and then change the action icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_menu);

        setupNavigationView();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                binding.drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationView() {
        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_settings:
                        Toast.makeText(BaseActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_help:
                        Toast.makeText(BaseActivity.this, "Help", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_aboutus:
                        Toast.makeText(BaseActivity.this, "About Us", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
}
