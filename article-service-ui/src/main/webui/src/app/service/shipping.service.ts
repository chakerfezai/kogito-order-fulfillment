import {inject, Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Payment} from "../_interface/payment";
import {Observable} from "rxjs";
import {Shipping} from "../_interface/shipping";

@Injectable({
  providedIn: 'root'
})
export class ShippingService {


  private http = inject(HttpClient);


  shippingActions(shipping: Shipping | undefined): Observable<any> {
    let url = '/shipping/action';
    return this.http.post(url, shipping);
  }
}
