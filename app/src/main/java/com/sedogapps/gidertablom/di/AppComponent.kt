package com.sedogapps.gidertablom.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
   // fun inject(viewModel: NewExpensePageViewModel) // NewExpensePageViewModel enjekte edilecek
    // fun inject(repository: NewExpensePageRepository)
}
