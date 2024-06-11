import {inject, Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Order} from "../_interface/order";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private http = inject(HttpClient);
  readonly url = '/api/order';

  createOrder(order: Order): Observable<string> {
    return this.http.post<string>(this.url, order);
  }

}
