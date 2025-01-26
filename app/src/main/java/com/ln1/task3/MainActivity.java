package com.ln1.task3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI Components
        EditText inputA = findViewById(R.id.inputA);
        EditText inputB = findViewById(R.id.inputB);
        EditText inputC = findViewById(R.id.inputC);
        Button btnSolve = findViewById(R.id.btnSolve);
        Button btnReset = findViewById(R.id.btnReset);
        TextView solutions = findViewById(R.id.solutions);

        // Solve Button Logic
        btnSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input for coefficients a, b, and c
                String inputAString = inputA.getText().toString();
                String inputBString = inputB.getText().toString();
                String inputCString = inputC.getText().toString();

                // Check if the user has input values for all coefficients
                if (inputAString.isEmpty() || inputBString.isEmpty() || inputCString.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in all the coefficients.", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    // Parse input values
                    double a = Double.parseDouble(inputAString);
                    double b = Double.parseDouble(inputBString);
                    double c = Double.parseDouble(inputCString);

                    // Check if the equation is linear (a = 0)
                    if (a == 0) {
                        if (b != 0) {
                            // Linear equation: bx + c = 0, solve for x
                            double root = -c / b;
                            solutions.setText(String.format("Solution: Root = %.2f", root));
                        } else {
                            solutions.setText("No solution. (Both 'a' and 'b' are zero.)");
                        }
                        return;
                    }

                    // For quadratic equation: ax² + bx + c = 0
                    double discriminant = b * b - 4 * a * c;

                    if (discriminant > 0) {
                        // Two real solutions
                        double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
                        double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
                        solutions.setText(String.format("Solutions:\nRoot 1 = %.2f\nRoot 2 = %.2f", root1, root2));
                    } else if (discriminant == 0) {
                        // One real solution (double root)
                        double root = -b / (2 * a);
                        solutions.setText(String.format("Solution: Root = %.2f", root));
                    } else {
                        // Complex solutions
                        double realPart = -b / (2 * a);
                        double imaginaryPart = Math.sqrt(-discriminant) / (2 * a);
                        solutions.setText(String.format("Complex Solutions:\nRoot 1 = %.2f + %.2fi\nRoot 2 = %.2f - %.2fi", realPart, imaginaryPart, realPart, imaginaryPart));
                    }
                } catch (NumberFormatException e) {
                    // Handle invalid input (non-numeric)
                    Toast.makeText(MainActivity.this, "Please enter valid numeric values for the coefficients.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Reset Button Logic
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear the input fields and reset the solutions
                inputA.setText("");
                inputB.setText("");
                inputC.setText("");
                solutions.setText("Solutions will appear here");
            }
        });
    }
}

