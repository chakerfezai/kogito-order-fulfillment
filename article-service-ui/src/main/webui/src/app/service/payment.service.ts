import {inject, Injectable, signal} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Payment} from "../_interface/payment";
import {filter, Observable, repeat, take} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  private http = inject(HttpClient);
  payment = signal<Payment | null>(null);


  getPayment(transactionId: string): Observable<Payment> {

    transactionId = transactionId.trim();

    const options = transactionId ?
      {params: new HttpParams().set('transactionId', transactionId)} : {};

    //let url = `/payment/byTransactionId/${transactionId}/order\`;
    return this.http.get<Payment>(`/payment/byTransactionId/${transactionId}/order`);
  }

  validatePayment(payment: Payment | undefined): Observable<any> {
    let url = '/payment/validate';
    return this.http.post(url, payment);
  }
}
