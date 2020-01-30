package es.raulprieto.inventory.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import es.raulprieto.inventory.R;
import es.raulprieto.inventory.databinding.ActivityDashboardBinding;
import es.raulprieto.inventory.ui.dashboard.dependency.DependencyActivity;

/**
 * Menu Activity from where we are accessing to the different sections of the App.
 *
 * @author Raúl Prieto Bailón
 * @version 1.0
 */
public class DashBoardActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityDashboardBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);

        setOnClickListeners();
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.btDependencies:
                intent = new Intent(this, DependencyActivity.class);
                break;
            case R.id.btSections:
//                intent = new Intent(this, .class);
                Toast.makeText(this,"Sections",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btProducts:
//                intent = new Intent(this, .class);
                Toast.makeText(this,"Products",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btProfile:
//                intent = new Intent(this, .class);
                Toast.makeText(this,"Profile",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btHelp:
//                intent = new Intent(this, .class);
                Toast.makeText(this,"Help",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btSettings:
//                intent = new Intent(this, .class);
                Toast.makeText(this,"Settings",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;

        }
        if (intent != null)
            startActivity(intent);
    }

    /**
     * Method used to set the OnClickListener on every DashBoard buttons
     */
    private void setOnClickListeners() {
        binding.btDependencies.setOnClickListener(this);
        binding.btSections.setOnClickListener(this);
        binding.btProducts.setOnClickListener(this);
        binding.btProfile.setOnClickListener(this);
        binding.btHelp.setOnClickListener(this);
        binding.btSettings.setOnClickListener(this);
    }
}
