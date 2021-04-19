# RickAndMortyApp
Документация к API - https://rickandmortyapi.com/documentation

1. Приложение состоит из 6-ти фрагментов и 1 активности:
- Активность с BottomNavigationView;
- Фрагмент со списком локаций;
- Фрагмент со списком персонажей;
- Фрагмент со списком эпизодов;
- Фрагмент с детальной информацией по персонажу;
- Фрагмент с детальной информацией по локации;
- Фрагмент с детальной информацией по эпизоду.

2. Реализация
- Сплэш-скрин
- Экраны списков (с пагинацией и swipe to refresh):
2.1. Персонажи - name, species, status, gender и image
2.2. Локации - name, type, dimension (без PagingLibrary)
2.3. Эпизоды - name, air_date, episod

В детальной информации:
- Персонажи - см п.2.1 + локация в которой он находится
- Локации - см п.2.2
- Эпизоды - см п.2.3

3. Стэк:
Dagger2, Retrofit2, Moshi/Json, RxJava2, Glide, Kotlin, MVVM (on LiveData), PagingLibrary
