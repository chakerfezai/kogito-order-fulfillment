import {inject, Injectable, signal} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Payment} from "../_interface/payment";
import {filter, Observable, repeat, take} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  private http = inject(HttpClient);
  payment = signal<Payment | null>(null);


  getPayment(transactionId: string): Observable<Payment> {
    let url = '/api/payment/byTransactionId' + '/' + transactionId;
    return this.http.get<Payment>(url)
      .pipe(
        repeat({delay: 1000}),
        filter((res) => res !== null),
        take(1)
      );
  }

  validatePayment(payment: Payment | undefined): Observable<any> {
    let url = '/api/payment/validate';
    return this.http.post(url, payment);
  }
}
