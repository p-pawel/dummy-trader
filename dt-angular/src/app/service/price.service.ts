import { Injectable, NgZone } from "@angular/core";
import { Observable } from "rxjs";
import {PriceData} from "../model/priceData";
import {environment} from "../../environments/environment";

export const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class PriceService {

  constructor(private ngZone: NgZone) {
  }

  getServerSentEvent(): Observable<PriceData> {
    return new Observable( (observer:any) => {

      const eventSource = new EventSource(API_URL + "/price/stream");
      eventSource.onmessage = event => this.ngZone.run(() => observer.next(JSON.parse(event.data)));
      eventSource.onerror = error => this.ngZone.run(() => observer.error(error));
    });
  }

}
