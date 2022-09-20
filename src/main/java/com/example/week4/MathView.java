package com.example.week4;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


@Route (value = "index1")
public class MathView extends VerticalLayout {
    private TextField num1, num2;
    private TextField ans;
    private HorizontalLayout h1;
    private Button plus, minus, multi, divide, mod, max;
    private Label op;
    public MathView(){
        num1 = new TextField("Number 1");
        num2 = new TextField("Number 2");
        op = new Label("Operator");
        ans = new TextField("Answer");
        h1 = new HorizontalLayout();
        plus = new Button("+");
        minus = new Button("-");
        multi = new Button("x");
        divide = new Button("/");
        mod = new Button("Mod");
        max = new Button("Max");

        h1.add(plus, minus, multi, divide, mod, max);
        add(num1, num2,op,h1,ans);

        plus.addClickListener(event ->{
            double n1 = Double.parseDouble(num1.getValue());
            double n2 = Double.parseDouble(num2.getValue());

            String out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8080/plus/"+n1+"/"+n2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
                    ans.setValue(out);
        });
        minus.addClickListener(event ->{
            double n1 = Double.parseDouble(num1.getValue());
            double n2 = Double.parseDouble(num2.getValue());

            String out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8080/minus/"+n1+"/"+n2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            ans.setValue(out);
        });
        multi.addClickListener(event ->{
            double n1 = Double.parseDouble(num1.getValue());
            double n2 = Double.parseDouble(num2.getValue());

            String out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8080/multi/"+n1+"/"+n2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            ans.setValue(out);
        });
        divide.addClickListener(event ->{
            double n1 = Double.parseDouble(num1.getValue());
            double n2 = Double.parseDouble(num2.getValue());

            String out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8080/divide/"+n1+"/"+n2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            ans.setValue(out);
        });
        mod.addClickListener(event ->{
            double n1 = Double.parseDouble(num1.getValue());
            double n2 = Double.parseDouble(num2.getValue());

            String out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8080/mod/"+n1+"/"+n2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            ans.setValue(out);
        });
        max.addClickListener(event ->{
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("n1", num1.getValue());
            formData.add("n2", num2.getValue());

            String out = WebClient.create()
                    .post()
                    .uri("http://127.0.0.1:8080/max/")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            ans.setValue(out);
        });
    }
}
