import {Injectable} from "@angular/core";
import {BehaviorSubject} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class MessageService {


  private totalItemsSubject$ = new BehaviorSubject<number>(0);

  updateCount(count: number | undefined) {
    if (count != null) {
      this.totalItemsSubject$.next(count);
    }
  }

  getCount() {
    return this.totalItemsSubject$;
  }
}
