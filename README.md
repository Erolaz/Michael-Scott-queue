## Очередь Майкла-Скотта

---
Для сборки проекта и запуска тестов `gradle build`

---
Фреймворк [Lincheck](https://kotlinlang.org/docs/lincheck-guide.html) использовался для тестирования многопоточности.

---

В нем есть разные стратеги тестирования, например [StressOptions](https://kotlinlang.org/docs/testing-strategies.html#stress-testing), 
которая создает несколько параллельных сценариев из заданных операций
[(подробнее как работает под капотом)](https://kotlinlang.org/docs/testing-strategies.html#how-stress-testing-works)

С помощью нее были реализованы тесты 1 и 2. 1 - простой стресс-тест, 2 - руками задаю количество потоков (один/много) и необходимое количество итераций в каждом.

Также фреймворк предоставляет стратегию [ModelChecking](https://kotlinlang.org/docs/testing-strategies.html#model-checking),
которая призвана помочь разработчику найти путь в программе, который приведет к ошибке. Lincheck маркирует места, где
может поменяться контекст, и по этим точкам может воспроизвести путь к ошибочному выходу. 

На основе этой стратегии построен тест 3, где реализация очереди Майкла-Скотта проверяется на по всем возможным путям исполнения,
а также применяется [checkObstructionFreedom](https://kotlinlang.org/docs/progress-guarantees.html) для проверки lock-freedom и wait-freedom