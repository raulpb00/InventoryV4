# Inventory - Version 2 {Fragments | MVP}

> Second version of Inventory 2019/2020

[![Awesome](https://cdn.rawgit.com/sindresorhus/awesome/d7305f38d29fed78fa85652e3a63e154dd8e8829/media/badge.svg)](https://github.com/raulpb00)[![Commits](https://img.shields.io/github/last-commit/pangeo-data/awesome-open-climate-science.svg?label=last%20contribution)](https://gitlab.com/raulpb00/inventory/commits/master) 

### DataBinding implemented in all classes.
#### Implement in App Graddle File:
```java
dataBinding{
        enabled true
    }
```
#### Layout of the Activity where we want to use DataBinding
```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android">
    <data /> 
    <ConstraintLayout> 
        OUR COMPONENTS
    </ConstraintLayout>
</layout>
```
#### Variable created at Data element and set at Java Fragment Class
```xml
<data>
    <variable
        name="dependency"
        type="es.raulprieto.inventory.data.db.model.Dependency" />
</data>
<com.google.android.material.textfield.TextInputEditText
                android:id="@+id/tedDependencyName"
                android:text="@{dependency.getName()}"
...></com.google.android.material.textfield.TextInputLayout>
```
```java
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
    // Inflate this fragment's layout
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dependency_manage, container, false);

    View view = binding.getRoot();

    Bundle bundle = getArguments();
    if (bundle != null)
        binding.setDependency((Dependency) bundle.getSerializable("dependency"));

    return view;
}
```
#### Build project in order to start using the compiled class of the XML Activity (it has its XML name) 
```java
public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        binding.btSignIn.setOnClickListener( . . .
```
---

#### Added Material Dependencies in order to use TextInputLayout and later ReciclerView. 
```java
implementation 'com.google.android.material:material:1.0.0'
```
>Changed on next updates, [see changes](https://github.com/raulpb00/InventoryV2#used-setanchorview-on-material-snackbar-in-order-to-place-it-above-the-floatingactionbuttom)
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


#### Changed colors of Password Toggle Button and CharacterCounter 
```xml
<com.google.android.material.textfield.TextInputLayout
            android:id="@+id/tilPassword"
            app:counterEnabled="true"
            app:counterMaxLength="12"
            app:counterTextAppearance="@style/AppTheme.CharacterCounter"
            app:passwordToggleEnabled="true"
            app:passwordToggleTint="@color/colorPrimaryDark" >
            
<style name="AppTheme.CharacterCounter" parent="TextAppearance.AppCompat.Small">
        <item name="android:textColor">@color/colorPrimaryDark</item>
</style>
```
### Applied theme to SignUp Layout in order to change the focused EditText colors
```xml
<androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:theme="@style/TextInputTheme">

<style name="TextInputTheme">
        <item name="colorControlActivated">@color/colorPrimaryDark</item>
    </style>
```
### Used unsplash.com in order to get random images as samples 
    unsplash.it/32/32 gives a random image at every call to that url

### Used setAnchorView on material Snackbar in order to place it above the FloatingActionButtom
>The method setAnchorView was introduced with 1.1.0-alpha01, so for now I'm using 
```xml
implementation 'com.google.android.material:material:1.1.0-beta02'
```
>Also, as I use MaterialCardViews too I had to change my AppTheme parent to Theme.MaterialComponents
```xml
Before
    <!-- Base application theme. -->
    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <!-- Customize your theme here. -->
    </style>
    
After
    <!-- Material application theme. -->
    <style name="AppTheme" parent="Theme.MaterialComponents.Light.DarkActionBar">
        <!-- Customize your theme here. -->
    </style>
```
>Then I can use Snackbar and anchor it to a Fab with this code. 
```java
Snackbar.make(fab, getString(R.string.action_delete), Snackbar.LENGTH_LONG)
                .setAnchorView(fab)
                .setAction(getString(R.string.action_undo), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        undoDelete(undoDeleted);
                    }
                })
                .show();
```
### Added .trim() on all input validations and storages
```java
binding.tedDependencyDescription.getText()).toString().trim()
```

<!-- Line Space-->
&nbsp;

| ▲ [Top](#) |
| --- |

## License

[![CC0](http://mirrors.creativecommons.org/presskit/buttons/88x31/svg/cc-zero.svg)](https://creativecommons.org/publicdomain/zero/1.0/)

## Contact
<a href="https://www.linkedin.com/in/raulprietobailon"><img src="https://thelinkedinman.com/wp-content/uploads/2016/02/View-my-LinkedIn-profile-image-3-300x140.png" title="LinkedIn" alt="LinkedIn" height="98" width="210" ></a>
