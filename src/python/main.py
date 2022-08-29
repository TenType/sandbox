import json
import time
import yfinance as yf

from importlib import import_module

def quote():
    print('Starting...')

    start = time.perf_counter()
    ticker = yf.Ticker('NFLX')
    end = time.perf_counter()

    print('Retrieved ticker info')
    print(json.dumps(ticker.info, sort_keys=True, indent=2))
    print(f'\nCompleted in {end - start}s')

def main():
    try:
        file = import_module('solution')
    except ModuleNotFoundError as error:
        print(f'Error: Missing file "solution.py"\n\nOriginal error:\n{error}')
        return

    try:
        output = file.solution(2, 2)
    except AttributeError as error:
        print(f'Error: Missing function "solution()"\n\nOriginal error:\n{error}')
        return

    print(output)
    assert output == 4

if __name__ == '__main__':
    quote()
