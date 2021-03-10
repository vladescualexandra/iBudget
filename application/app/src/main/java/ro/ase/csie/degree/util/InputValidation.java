package ro.ase.csie.degree.util;

import android.content.Context;
import android.text.Editable;
import android.util.Patterns;

import ro.ase.csie.degree.R;
import ro.ase.csie.degree.model.Balance;
import ro.ase.csie.degree.model.TransactionType;

import com.google.android.material.textfield.TextInputEditText;


public class InputValidation {

    private final Context context;

    public InputValidation(Context context) {
        this.context = context;
    }


    public boolean nameValidation(TextInputEditText tiet) {
        if (tiet.getText().toString().trim().length() < 3) {
            tiet.setError(context.getString(R.string.error_invalid_name));
            return false;
        } else {
            tiet.setError(null);
            return true;
        }
    }

    public boolean emailValidation(TextInputEditText tiet) {
        if (!Patterns.EMAIL_ADDRESS.matcher(tiet.getText().toString().trim()).matches()) {
            tiet.setError(context.getString(R.string.error_invalid_email));
            return false;
        } else {
            tiet.setError(null);
            return true;
        }
    }

    public boolean passwordValidation(TextInputEditText tiet) {
        if (tiet.getText().toString().trim().length() < 8) {
            tiet.setError(context.getString(R.string.error_invalid_password));
            return false;
        } else {
            tiet.setError(null);
            return true;
        }
    }

    public boolean loginValidation(TextInputEditText email, TextInputEditText password) {
        return emailValidation(email) && passwordValidation(password);
    }

    public boolean registerValidation(TextInputEditText name, TextInputEditText email, TextInputEditText password) {
        return nameValidation(name) && emailValidation(email) && passwordValidation(password);
    }

    public boolean availableAmountValidation(TextInputEditText available_amount) {
        if (Double.parseDouble(available_amount.getText().toString()) >= 0.0) {
            available_amount.setError(null);
            return true;
        } else {
            available_amount.setError(context.getString(R.string.invalid_available_amount));
            return false;
        }

    }

    public boolean amountValidation(TransactionType type, double available_amount, double amount) {
        if (type.equals(TransactionType.INCOME)) {
            return amount > 0;
        } else {
            return available_amount >= amount && amount > 0;
        }
    }
}
