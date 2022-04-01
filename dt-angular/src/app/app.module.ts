import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {ExchangeContainerComponent} from './container/exchange-container/exchange-container.component';
import {MarketInfoComponent} from './presentation/market-info/market-info.component';
import {PlaceOrderFormComponent} from './presentation/place-order-form/place-order-form.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    ExchangeContainerComponent,
    MarketInfoComponent,
    PlaceOrderFormComponent,
  ],
  imports: [
    BrowserModule,
    NgbModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
