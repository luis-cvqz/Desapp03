package com.desapp.desapp03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private boolean emptyFields(){
        EditText editName = findViewById(R.id.edit_name);
        EditText editBirthDate = findViewById(R.id.edit_birthdate);
        RadioGroup radioGroupSex = findViewById(R.id.rgroup_sex);
        EditText editPhone = findViewById(R.id.edit_phone);
        EditText editEmail = findViewById(R.id.edit_email);
        TextInputEditText editPassword = findViewById(R.id.edit_password);
        TextInputEditText editPasswordConfirmation = findViewById(R.id.edit_password_confirmation);

        return editName.getText().toString().isEmpty()
            || editBirthDate.getText().toString().isEmpty()
            || radioGroupSex.getCheckedRadioButtonId() == -1
            || editEmail.getText().toString().isEmpty()
            || editPhone.getText().toString().isEmpty()
            || editPassword.getText().toString().isEmpty()
            || editPasswordConfirmation.getText().toString().isEmpty();
    }

    private boolean validEmail(){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        EditText editEmail = findViewById(R.id.edit_email);

        String email = editEmail.getText().toString().trim();

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    private boolean matchingPasswords(){
        TextInputEditText editPassword = findViewById(R.id.edit_password);
        TextInputEditText editPasswordConfirmation = findViewById(R.id.edit_password_confirmation);

        String password = editPassword.getText().toString();
        String passwordConfirmation = editPasswordConfirmation.getText().toString();

        return passwordConfirmation.equals(password);
    }

    private void showInvalidEmailToast(){
        Toast.makeText(this, R.string.tst_invalid_email, Toast.LENGTH_SHORT).show();
    }

    private void showEmptyFieldsToast(){
        Toast.makeText(this, R.string.tst_fill_all_fields, Toast.LENGTH_SHORT).show();
    }

    private boolean validFields(){
        TextView matchingPasswordsAlert = findViewById(R.id.txt_password_mismatch);

        boolean fieldsAreValid = true;

        if (emptyFields()){
            showEmptyFieldsToast();
            fieldsAreValid = false;
        } else if (validEmail()) {
            showInvalidEmailToast();
            fieldsAreValid = false;
        } else if (!matchingPasswords()) {
            matchingPasswordsAlert.setVisibility(View.VISIBLE);
            fieldsAreValid = false;
        }

        return fieldsAreValid;
    }

    public void clearFields(View view) {
        EditText editName = findViewById(R.id.edit_name);
        EditText editBirthDate = findViewById(R.id.edit_birthdate);
        RadioGroup radioGroupSex = findViewById(R.id.rgroup_sex);
        EditText editPhone = findViewById(R.id.edit_phone);
        EditText editEmail = findViewById(R.id.edit_email);
        TextInputEditText editPassword = findViewById(R.id.edit_password);
        TextInputEditText editPasswordConfirmation = findViewById(R.id.edit_password_confirmation);

        editName.getText().clear();
        editBirthDate.getText().clear();
        radioGroupSex.clearCheck();
        editEmail.getText().clear();
        editPhone.getText().clear();
        editPassword.getText().clear();
        editPasswordConfirmation.getText().clear();

        Toast.makeText(this, R.string.tst_fields_cleared, Toast.LENGTH_SHORT).show();
    }

    public void registerUser(View view) {
        TextView matchingPasswordsAlert = findViewById(R.id.txt_password_mismatch);

        if (validFields()){
            Toast.makeText(this, R.string.tst_user_registered, Toast.LENGTH_SHORT).show();
            matchingPasswordsAlert.setVisibility(View.INVISIBLE);
        }
    }
}