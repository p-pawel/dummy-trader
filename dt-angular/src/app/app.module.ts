import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {ExchangeContainerComponent} from './container/exchange-container/exchange-container.component';
import {MarketInfoComponent} from './presentation/market-info/market-info.component';

@NgModule({
  declarations: [
    AppComponent,
    ExchangeContainerComponent,
    MarketInfoComponent,
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
