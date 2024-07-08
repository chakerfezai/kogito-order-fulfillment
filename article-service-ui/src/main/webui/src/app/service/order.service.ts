import {inject, Injectable, signal} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Order} from "../_interface/order";
import {Observable, tap} from "rxjs";
import {Product} from "../_interface/product";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private http = inject(HttpClient);
  readonly url = '/order';
  orders = signal<Order[]>([])

  createOrder(order: Order): Observable<string> {
    return this.http.post<string>(this.url, order);
  }


  allOrders(): Observable<Order[]> {
    return this.http.get<Order[]>('/order/all').pipe(
      tap((order => this.orders.set(order)))
    );
  }

}
