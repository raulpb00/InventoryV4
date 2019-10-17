# Inventory - Version 1 {UI}

#### Databinding used in all classes.

Implement in App Graddle File:
```java
dataBinding{
        enabled true
    }
```
Layout of the Activity where we want to use DataBinding
```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android">
    <data /> 
    <ConstraintLayout> 
        OUR COMPONENTS
    </ConstraintLayout>
</layout>
```
Build project in order to start using the compiled class of the XML Activity (it has its XML name) 
```java
public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        binding.btSignIn.setOnClickListener( . . .
```

#### Added Material Dependencies in order to use TextInputLayout and later ReciclerView. 
```java
implementation 'com.google.android.material:material:1.0.0'
```


    