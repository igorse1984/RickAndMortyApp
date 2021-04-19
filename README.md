# RickAndMortyApp
Документация к API - https://rickandmortyapi.com/documentation

Приложение состоит из 6-ти фрагментов и 1 активности.
Активность с BottomNavigationView
- Фрагмент со списком локаций
- Фрагмент со списком персонажей
- Фрагмент со списком эпизодов
- Фрагмент с детальной информацией по персонажу
- Фрагмент с детальной информацией по локации
- Фрагмент с детальной информацией по эпизоду

Сплэш-скрин
Экраны списков (с пагинацией и swipe to refresh):
1. Персонажи - name, species, status, gender и image
2. Локации - name, type, dimension (без PagingLibrary)
3. Эпизоды - name, air_date, episod

В детальной информации:
- Персонажи - см п.1 + локация в которой он находится
- Локации - см п.2
- Эпизоды - см п.3

3. Стэк:
Dagger2, Retrofit2, Moshi/Json, RxJava2, Glide, Kotlin, MVVM (on LiveData), PagingLibrary
