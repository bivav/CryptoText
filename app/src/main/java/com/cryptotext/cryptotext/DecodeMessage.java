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

public class DecodeMessage extends Fragment {

    String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private EditText to_convert, converted_decode_message;
    Button convert_decode_btn;

    RadioGroup letter_selection;
    RadioButton one_letter, two_letters, three_letters;

    public DecodeMessage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.decode_fragment, container, false);

        // decoding
        to_convert = (EditText)view.findViewById(R.id.to_convert);
        converted_decode_message = (EditText)view.findViewById(R.id.converted_decode_message);

        // Choose the decoding
        letter_selection = (RadioGroup)view.findViewById(R.id.letter_selection);
        one_letter = (RadioButton)view.findViewById(R.id.one_letter);
        one_letter.setChecked(true);
        two_letters = (RadioButton)view.findViewById(R.id.two_letters);
        three_letters = (RadioButton)view.findViewById(R.id.three_letters);

        // buttons
        convert_decode_btn = (Button)view.findViewById(R.id.convert_decode_btn);

        convert_decode_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String temp = to_convert.getText().toString();
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
                                    case "A":
                                        splitText[i] = letters[25];
                                        break;
                                    default:
                                        splitText[i] = letters[j - 1];
                                        break;
                                }
                                break;
                            } else if (two_letters.isChecked()) {
                                switch (letters[j]) {
                                    case "A":
                                        splitText[i] = letters[24];
                                        break;
                                    case "B":
                                        splitText[i] = letters[25];
                                        break;
                                    default:
                                        splitText[i] = letters[j - 2];
                                        break;
                                }
                                break;
                            } else {

                                switch (letters[j]) {
                                    case "A":
                                        splitText[i] = letters[23];
                                        break;
                                    case "B":
                                        splitText[i] = letters[24];
                                        break;
                                    case "C":
                                        splitText[i] = letters[25];
                                        break;
                                    default:
                                        splitText[i] = letters[j - 3];
                                        break;
                                }

                                break;
                            }
                        }
                    }
                }
                String joinedString = TextUtils.join("", splitText);
                Log.i("testing", joinedString);
                converted_decode_message.setText(joinedString);

            }
        });

        return view;
    }

    public void resetText() {
        to_convert.setText("");
        converted_decode_message.setText("");
    }

}
