import {Component, Input} from '@angular/core';
import {PriceData} from "../../model/priceData";

@Component({
  selector: 'app-market-info',
  templateUrl: './market-info.component.html',
  styleUrls: ['./market-info.component.scss']
})
export class MarketInfoComponent {

  @Input()
  ticker!: string;

  @Input()
  price!: PriceData | null;

}
