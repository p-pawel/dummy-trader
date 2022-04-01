import {Injectable} from '@angular/core';
import {PlaceOrderRequest} from "../model/placeOrderRequest";
import {Observable} from "rxjs";
import {PlaceOrderResponse} from "../model/placeOrderResponse";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

export const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class PlaceOrderService {

  constructor(private http: HttpClient) {
  }

  placeOrder(placeOrderRequest: PlaceOrderRequest): Observable<PlaceOrderResponse> {
    return this.http.post<PlaceOrderResponse>(`${API_URL}/order/`, placeOrderRequest);
  }
}
