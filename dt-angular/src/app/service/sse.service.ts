import {Injectable, NgZone} from "@angular/core";
import {Observable} from "rxjs";
import {PriceData} from "../model/priceData";
import {environment} from "../../environments/environment";
import {BalanceData} from "../model/balanceData";

export const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class SseService {

  constructor(private ngZone: NgZone) {
  }

  getPrice(): Observable<PriceData> {
    return new Observable((observer: any) => {
      const eventSource = new EventSource(API_URL + "/price");
      eventSource.onmessage = event => this.ngZone.run(() => observer.next(JSON.parse(event.data)));
      eventSource.onerror = error => this.ngZone.run(() => observer.error(error));
    });
  }

  getBalance(): Observable<BalanceData> {
    return new Observable((observer: any) => {
      const eventSource = new EventSource(API_URL + "/balance");
      eventSource.onmessage = event => this.ngZone.run(() => observer.next(JSON.parse(event.data)))
      eventSource.onerror = error => this.ngZone.run(() => observer.error(error))
    });
  }

}
