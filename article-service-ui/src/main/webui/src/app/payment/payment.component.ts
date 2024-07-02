import {Component, inject, OnInit} from '@angular/core';
import {PaymentService} from "../service/payment.service";
import {LocalStorageService} from "../service/local.storage.service";
import {Payment} from "../_interface/payment";
import {Customer} from "../_interface/customer";
import {PaymentMethod} from "../_interface/enum/payment.method";

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrl: './payment.component.css'
})
export class PaymentComponent implements OnInit {

  localStorageService = inject(LocalStorageService);
  paymentService = inject(PaymentService);
  payment: Payment | undefined;
  user: Customer | undefined;

  ngOnInit(): void {
    let transactionId = this.localStorageService.getItem("transactionId");
    this.user = JSON.parse(<string>this.localStorageService.getItem("user"));
    if (transactionId != null) {
      this.paymentService.getPayment(transactionId)
        .subscribe(value => this.payment = value);
    }
  }

  pay() {
    // @ts-ignore
    this.payment.method = PaymentMethod.BANK_TRANSFER;
    this.paymentService.validatePayment(this.payment).subscribe(value => console.log(value))
  }


}
