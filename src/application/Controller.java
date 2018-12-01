package application;

import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Controller implements Initializable {

	private static int p;
	private static int q;
	private static int e;

	@FXML
	private Label n_label;

	@FXML
	private Label f_label;

	@FXML
	private Label d_label;

	@FXML
	private JFXButton en_button;

	@FXML
	private JFXButton de_button;

	@FXML
	private JFXTextArea text_field;

	@FXML
	private JFXTextField p_field;

	@FXML
	private JFXTextField q_field;

	@FXML
	private JFXTextField e_field;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		text_field.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				text_field.setFocusColor(Color.web("#4ab0fe"));
			}
		});
		p_field.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				p_field.setFocusColor(Color.web("#4ab0fe"));
			}
		});
		q_field.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				q_field.setFocusColor(Color.web("#4ab0fe"));
			}
		});
		e_field.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				e_field.setFocusColor(Color.web("#4ab0fe"));
			}
		});
	}

	@FXML
	void encyptMethod(ActionEvent event) {
		BigInteger m, n;
		char[] arr;

		if (text_field.getText().equals("")) {
			text_field.setFocusColor(Color.RED);
			text_field.requestFocus();
		} else if (p_field.getText().equals("")) {
			p_field.setFocusColor(Color.RED);
			p_field.requestFocus();
		} else if (q_field.getText().equals("")) {
			q_field.setFocusColor(Color.RED);
			q_field.requestFocus();
		} else if (e_field.getText().equals("")) {
			e_field.setFocusColor(Color.RED);
			e_field.requestFocus();
		} else {
			p = Integer.parseInt(p_field.getText());
			q = Integer.parseInt(q_field.getText());
			e = Integer.parseInt(e_field.getText());

			n = new BigInteger(String.valueOf(p * q));

			arr = text_field.getText().toCharArray();
			text_field.clear();

			for (int i = 0; i < arr.length; i++) {
				m = new BigInteger(String.valueOf((int) arr[i]));
				text_field.setText(text_field.getText() + (char) (m.pow(e).mod(n).intValue() + 20));
			}

			n_label.setText("n = " + p * q);
			f_label.setText("φ(n) = " + EulersTotientFunction());
			d_label.setText("d = " + ExtendedEuclidAlgorithm());

		}
	}

	@FXML
	void decyptMethod(ActionEvent event) {
		BigInteger c, n;
		char[] arr;

		if (text_field.getText().equals("")) {
			text_field.setFocusColor(Color.RED);
			text_field.requestFocus();
		} else if (p_field.getText().equals("")) {
			p_field.setFocusColor(Color.RED);
			p_field.requestFocus();
		} else if (q_field.getText().equals("")) {
			q_field.setFocusColor(Color.RED);
			q_field.requestFocus();
		} else if (e_field.getText().equals("")) {
			e_field.setFocusColor(Color.RED);
			e_field.requestFocus();
		} else {
			p = Integer.parseInt(p_field.getText());
			q = Integer.parseInt(q_field.getText());
			e = Integer.parseInt(e_field.getText());

			int d = ExtendedEuclidAlgorithm();

			n = new BigInteger(String.valueOf(p * q));

			arr = text_field.getText().trim().toCharArray();
			text_field.clear();

			for (int i = 0; i < arr.length; i++) {
				c = new BigInteger(String.valueOf((int) arr[i] - 20));
				text_field.setText(text_field.getText() + (char) ((c.pow(d).mod(n)).intValue()));
			}

			n_label.setText("n = " + p * q);
			f_label.setText("φ(n) = " + EulersTotientFunction());
			d_label.setText("d = " + ExtendedEuclidAlgorithm());

		}

	}

	// Euler's totient function counts the positive integers up to a given integer n
	// that are relatively prime to n.
	public static int EulersTotientFunction() {

		return (p - 1) * (q - 1);
	}

	// Extended Euclidean algorithm for finding 'd' value
	public static int ExtendedEuclidAlgorithm() {

		int r0, r1, s0, s1, t0, t1, q, a, b, f;
		f = EulersTotientFunction();

		if (e > f) {
			a = e;
			b = f;
		} else {
			a = f;
			b = e;
		}

		r0 = a;
		s0 = 1;
		t0 = 0;
		r1 = b;
		s1 = 0;
		t1 = 1;

		while (true) {
			int remainder;

			q = r0 / r1;

			remainder = r0 - (q * r1);
			r0 = r1;
			r1 = remainder;
			if (r1 == 0)
				break;

			remainder = s0 - (q * s1);
			s0 = s1;
			s1 = remainder;

			remainder = t0 - (q * t1);
			t0 = t1;
			t1 = remainder;
		}
		int endValue = e > f ? s1 : t1;

		// If 'endValue' is a negative value, it should be converted to positiv
		int k = 1;
		while (endValue < 0) {
			if (e > f)
				endValue = s1 + k * b;
			else
				endValue = t1 - (-k * a);
			k++;
		}
		return endValue;
	}
}
