# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Calendar Versioning](https://calver.org/) of
the following form: YYYY.0M.0D.

## 2026.04.01

### Added

- Added `ScoreTrackerSecondary` abstract class in `src/components/scoretracker`
- Implemented all secondary methods: `addFreeThrow`, `addTwoPointer`, `addThreePointer`, `isTie`, `leader`, and `resetGame`
- Implemented `toString()`, `equals(Object)`, and `hashCode()` using only kernel observers

### Updated

- Updated Part 4 abstract-class documentation with concrete branch/progress notes and a summary of design changes since interfaces

## 2026.03.10

### Added

- Designed `ScoreTrackerKernel` and `ScoreTracker` interfaces in `src/components/scoretracker`
- Added client-facing JavaDoc contracts for kernel and enhanced method headers
- Added a hierarchy diagram and interface-design update notes to Part 3 documentation

### Updated

- Refined API naming to `score`, `fouls`, and `period` for compact kernel operations
- Replaced nullable leader-reporting with a contract-based `leader()` precondition

## 2026.02.05

### Added

- Designed a score tracker component
- Designed a simple counter component
- Designed a simple yes/no flag component
- Completed additional considerations for each component
- Documented post-assignment notes in the brainstorming document
