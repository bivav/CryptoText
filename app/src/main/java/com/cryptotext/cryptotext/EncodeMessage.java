package com.cryptotext.cryptotext;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class EncodeMessage extends Fragment {

    String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private EditText input_message, converted_encoded_message;
    Button convert_encode_btn;
    RadioGroup letter_selection;
    RadioButton one_letter, two_letters, three_letters;

    public EncodeMessage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.encode_fragment, container, false);

        //encoding
        input_message = (EditText)view.findViewById(R.id.input_message);
        converted_encoded_message = (EditText)view.findViewById(R.id.converted_encoded_message);

        // Choose the encoding
        letter_selection = (RadioGroup)view.findViewById(R.id.letter_selection);
        one_letter = (RadioButton)view.findViewById(R.id.one_letter);
        one_letter.setChecked(true);
        two_letters = (RadioButton)view.findViewById(R.id.two_letters);
        three_letters = (RadioButton)view.findViewById(R.id.three_letters);

        // Magic button
        convert_encode_btn = (Button)view.findViewById(R.id.convert_encode_btn);

        convert_encode_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String temp = input_message.getText().toString();
                String[] splitText = new String[temp.length()];

                // Getting all characters individually
                for (int i = 0; i < temp.length(); i++) {
                    splitText[i] = String.valueOf(temp.charAt(i));
                    splitText[i] = splitText[i].toUpperCase();

                    // checking if equal in the letters dictionary or array
                    for (int j = 0; j < letters.length; j++) {

                        // if found change the letter to next letter and break the loop
                        if (splitText[i].equals(letters[j])) {

                            if (one_letter.isChecked()) {
                                switch (letters[j]) {

                                    case "Z":
                                        splitText[i] = letters[0];
                                        break;
                                    default:
                                        splitText[i] = letters[j + 1];
                                        break;
                                }
                                break;
                            } else if (two_letters.isChecked()) {

                                switch (letters[j]) {
                                    case "Y":
                                        splitText[i] = letters[0];
                                        break;
                                    case "Z":
                                        splitText[i] = letters[1];
                                        break;
                                    default:
                                        splitText[i] = letters[j + 2];
                                        break;
                                }
                                break;
                            } else {

                                switch (letters[j]) {
                                    case "X":
                                        splitText[i] = letters[0];
                                        break;
                                    case "Y":
                                        splitText[i] = letters[1];
                                        break;
                                    case "Z":
                                        splitText[i] = letters[2];
                                        break;
                                    default:
                                        splitText[i] = letters[j + 3];
                                        break;
                                }

                                break;
                            }

                        }
                    }
                }
                String joinedString = TextUtils.join("", splitText);
                Log.i("testing", joinedString);

                converted_encoded_message.setText(joinedString);
            }
        });


        return view;
    }

    public void resetText() {
        input_message.setText("");
        converted_encoded_message.setText("");
    }

}
