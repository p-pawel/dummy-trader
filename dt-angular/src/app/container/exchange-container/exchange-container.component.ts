import {Component} from '@angular/core';
import {Observable} from "rxjs";
import {PriceData} from "../../model/priceData";
import {SseService} from "../../service/sse.service";
import {PlaceOrderRequest} from "../../model/placeOrderRequest";
import {PlaceOrderService} from "../../service/place-order.service";
import {HttpErrorResponse} from "@angular/common/http";
import {BalanceData} from "../../model/balanceData";


@Component({
  selector: 'app-exchange-container',
  templateUrl: './exchange-container.component.html',
  styleUrls: ['./exchange-container.component.scss']
})
export class ExchangeContainerComponent {

  price$: Observable<PriceData> = this.sseService.getPrice();
  balance$: Observable<BalanceData> = this.sseService.getBalance();

  price!: PriceData | null;
  balance!: BalanceData | null;
  pendingRequest: boolean = false;

  constructor(
    private sseService: SseService,
    private placeOrderService: PlaceOrderService,
  ) {
    this.price$.subscribe(e => this.price = e);
    this.balance$.subscribe(e => this.balance = e);
  }

  onPlaceOrder(placeOrderRequest: PlaceOrderRequest) {
    this.pendingRequest = true;
    this.placeOrderService.placeOrder(placeOrderRequest)
      .subscribe(
        {
          next:
            () => {
              this.pendingRequest = false;
            },
          error: (error: HttpErrorResponse) => {
            window.alert(error.error?.error);
            this.pendingRequest = false;
          }
        }
      );
  }
}
