import {Component} from '@angular/core';
import {Observable, tap} from "rxjs";
import {PriceData} from "../../model/priceData";
import {PriceService} from "../../service/price.service";


@Component({
  selector: 'app-exchange-container',
  templateUrl: './exchange-container.component.html',
  styleUrls: ['./exchange-container.component.scss']
})
export class ExchangeContainerComponent {

  price$: Observable<PriceData> = this.sseService
      .getServerSentEvent();

  constructor(
    private sseService: PriceService,
  ) {
  }

}
