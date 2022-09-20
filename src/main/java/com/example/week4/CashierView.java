package com.example.week4;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.client.WebClient;

@Route (value = "index2")
public class CashierView extends VerticalLayout {
    private TextField b1000, b500, b100, b20, b10, b5, b1;
    public CashierView(){

        TextField changeamount = new TextField("เงินทอน");
        Button cal = new Button("คำนวณเงินทอน");
        b1000 = new TextField();
        b500 = new TextField();
        b100 = new TextField();
        b20 = new TextField();
        b10 = new TextField();
        b5 = new TextField();
        b1 = new TextField();

        b1000.setPlaceholder("$1000:");
        b500.setPlaceholder("$500:");
        b100.setPlaceholder("$100:");
        b20.setPlaceholder("$20:");
        b10.setPlaceholder("$10:");
        b5.setPlaceholder("$5:");
        b1.setPlaceholder("$1:");


        add(changeamount, cal, b1000, b500, b100, b20, b10, b5, b1);

        cal.addClickListener(event ->{
            int n1 = Integer.parseInt(changeamount.getValue());

            Change out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8080/getChange/"+n1+"/")
                    .retrieve()
                    .bodyToMono(Change.class)
                    .block();
            b1000.setValue("$1000:"+out.getB1000());
            b500.setValue("$500:"+out.getB500());
            b100.setValue("$100:"+out.getB100());
            b20.setValue("$20:"+out.getB20());
            b10.setValue("$10:"+out.getB10());
            b5.setValue("$5:"+out.getB5());
            b1.setValue("$1:"+out.getB1());
        });
    }
}
