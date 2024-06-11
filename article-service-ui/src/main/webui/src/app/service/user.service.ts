import {inject, Injectable, signal} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable, tap} from "rxjs";
import {LocalStorageService} from "./local.storage.service";
import {Customer} from "../_interface/customer";

@Injectable({
  providedIn: 'root'
})
export class UserService {


  private http = inject(HttpClient);
  private localStorageService = inject(LocalStorageService);
  user = signal<Customer | null>(null);
  readonly url = '/api/customer/1';


  getUser(): Observable<Customer> {
    return this.http.get<Customer>(this.url).pipe(
      tap(user => {
        this.localStorageService.setItem("user", JSON.stringify(user))
        this.user.set(user)
      })
    );
  }
}
