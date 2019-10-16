package es.raulprieto.inventory;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import es.raulprieto.inventory.databinding.ActivityDashboardBinding;

/**
 * Menu Activity from where we are accessing to the different sections of the App.
 * @author Raúl Prieto Bailón
 * @version 1.0
 */
public class DashBoardActivity extends AppCompatActivity {

    ActivityDashboardBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);





    }


}
