import {Product} from "../_interface/product";
import {inject, Injectable, signal} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable, tap} from "rxjs";

@Injectable({
  providedIn: 'root'
})

export class ProductService {
  private http = inject(HttpClient);
  products = signal<Product[]>([])
  readonly url = '/api/product?sort=id';


  list(): Observable<Product[]> {
    return this.http.get<Product[]>(this.url).pipe(
      tap((products => this.products.set(products)))
    );
  }
}
