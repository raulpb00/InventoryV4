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

#### The SignUp activity will focus a field and show the keyboard if there were any errors at it
```java
private boolean validateUser(String user) {
        if (TextUtils.isEmpty(user)) {
            binding.tilUser.setError(getString(R.string.errUserEmpty));
            requestFocus(binding.edUser);
            
private void requestFocus(View view) { 
        if (view.requestFocus())  showSoftInput(view);}

    public void showSoftInput(View view) {
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, 0); }
```

#### Added singleTask launchMode at Manifest.xml in order to avoid creating multiple instances of an activity
```xml
<activity
            android:name=".ui.LoginActivity"
            android:launchMode="singleTask">
```



    