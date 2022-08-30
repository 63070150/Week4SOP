package com.example.week4;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;


@Route (value = "index1")
public class MathView extends VerticalLayout {
    private TextField n1, n2;
    private TextArea ans;
    private HorizontalLayout h1;
    private Button plus, minus, multi, divide, mod, max;
    public MathView(){
        n1 = new TextField("Number 1");
        n2 = new TextField("Number 2");
        Label op = new Label("Operator");
        ans = new TextArea("Answer");
        h1 = new HorizontalLayout();
        plus = new Button("+");
        minus = new Button("-");
        multi = new Button("x");
        divide = new Button("/");
        mod = new Button("Mod");
        max = new Button("Max");

        h1.add(plus, minus, multi, divide, mod, max);
        add(n1,n2,op,h1,ans);

        plus.addClickListener(event ->{
            double num1 = Double.parseDouble(n1.getValue());
            double num2 = Double.parseDouble(n2.getValue());

            String out = WebClient.create()
                    .get()
                    .uri("/plus/{n1}/{n2}")
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
                    ans.setValue(out);
        });
    }
}
